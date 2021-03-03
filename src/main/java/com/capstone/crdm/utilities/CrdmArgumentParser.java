package com.capstone.crdm.utilities;

import com.capstone.crdm.exception.CrdmIllegalArgumentException;
import com.github.tennaito.rsql.misc.ArgumentFormatException;
import com.github.tennaito.rsql.misc.DefaultArgumentParser;

import java.time.Instant;

@SuppressWarnings({"unchecked", "unused"})
public class CrdmArgumentParser extends DefaultArgumentParser {

    @Override
    public <T> T parse(String argument, Class<T> type) throws ArgumentFormatException, IllegalArgumentException {
        if (type.equals(Instant.class)) {
            try {
                return (T) Instant.parse(argument);
            } catch (Exception ex) {
                throw new CrdmIllegalArgumentException("Cannot parse query. Detailed: " + ex.getLocalizedMessage(), ex);
            }
        }

        try {
            return super.parse(argument, type);
        } catch (Exception ex) {
            throw new CrdmIllegalArgumentException("Cannot parse query. Detailed: " + ex.getLocalizedMessage(), ex);
        }
    }

}
