package com.capstone.crdm.services;

import com.capstone.crdm.constants.EntityStatus;
import com.capstone.crdm.constants.OperationMode;
import com.capstone.crdm.entities.FormulaEntity;
import com.capstone.crdm.entities.PhaseEntity;
import com.capstone.crdm.exception.CrdmIllegalArgumentException;
import com.capstone.crdm.repositories.FormulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class FormulaService extends CrdmService<FormulaEntity, Integer, FormulaRepository> {

    private final FormulaRepository formulaRepository;

    private final MaterialService materialService;

    public FormulaService(FormulaRepository productRepository, MaterialService materialService) {
        super(FormulaEntity.class);
        this.formulaRepository = productRepository;
        this.materialService = materialService;
    }

    @Override
    protected FormulaRepository getRepository() {
        return this.formulaRepository;
    }

    @Override
    protected void validate(FormulaEntity formula, OperationMode operationMode) {
        var details = formula.getDetails();
        if (details == null || details.isEmpty()) {
            throw new CrdmIllegalArgumentException("Formula details cannot be empty");
        }

        var phases = formula.getPhases();
        if (phases == null || phases.isEmpty()) {
            throw new CrdmIllegalArgumentException("At least one phase must be defined");
        }

        details.forEach(detail -> {
            var materialId = detail.getMaterialId();
            var percentage = detail.getPercentage();

            var accumulatedPercentage = 0D;

            for (PhaseEntity phase : phases) {
                var phaseDetails = phase.getDetails().stream().filter(pd -> pd.getMaterialId() == materialId).findFirst().orElse(null);
                if (phaseDetails != null) {
                    accumulatedPercentage += phaseDetails.getPercentage();
                }
            }
            if (accumulatedPercentage != percentage) {
                var material = this.materialService.findById(materialId);
                throw new CrdmIllegalArgumentException("Accumulated percentage of material [" + material.getName() + "] in phases is not equal to declared ratio on the formula");
            }
        });

    }

    public void updateFormulaStatus(Integer id, String status) {
        var formula = this.findById(id);
        formula.setStatus(status);
        this.formulaRepository.save(formula);
    }

    public void upgrade(FormulaEntity formula) {
        // remove ID for children
        formula.getPhases().forEach(phase -> {
            phase.setId(null);
            phase.getDetails().forEach(detail -> {
                detail.setId(null);
            });
        });

        formula.getDetails().forEach(detail -> detail.setId(null));

        // set upgrade information for the formula
        formula.setUpgradedFrom(formula.getId());
        formula.setVersionId(formula.getVersionId() + 1);

        // mark the old formula as "out of date"
        var oldFormula = this.findById(formula.getId());
        oldFormula.setStatus(EntityStatus.OUT_OF_DATE);
        this.formulaRepository.save(formula);

        this.create(formula);
    }

}
