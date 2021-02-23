--SET statement_timeout = 0;
--SET lock_timeout = 0;
--SET idle_in_transaction_session_timeout = 0;
--SET client_encoding = 'UTF8';
--SET standard_conforming_strings = on;
----SELECT pg_catalog.set_config('search_path', '', false);
--SET check_function_bodies = false;
--SET xmloption = content;
--SET client_min_messages = warning;
--SET row_security = off;

--USE [master]
--GO
--/****** Object:  Database [CRDM]    Script Date: 2/23/2021 2:17:02 PM ******/
--GO
--IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
--begin
--EXEC [CRDM].[dbo].[sp_fulltext_database] @action = 'enable'
--end
--GO
--ALTER DATABASE [CRDM] SET ANSI_NULL_DEFAULT OFF
--GO
--ALTER DATABASE [CRDM] SET ANSI_NULLS OFF
--GO
--ALTER DATABASE [CRDM] SET ANSI_PADDING OFF
--GO
--ALTER DATABASE [CRDM] SET ANSI_WARNINGS OFF
--GO
--ALTER DATABASE [CRDM] SET ARITHABORT OFF
--GO
--ALTER DATABASE [CRDM] SET AUTO_CLOSE OFF
--GO
--ALTER DATABASE [CRDM] SET AUTO_SHRINK OFF
--GO
--ALTER DATABASE [CRDM] SET AUTO_UPDATE_STATISTICS ON
--GO
--ALTER DATABASE [CRDM] SET CURSOR_CLOSE_ON_COMMIT OFF
--GO
--ALTER DATABASE [CRDM] SET CURSOR_DEFAULT  GLOBAL
--GO
--ALTER DATABASE [CRDM] SET CONCAT_NULL_YIELDS_NULL OFF
--GO
--ALTER DATABASE [CRDM] SET NUMERIC_ROUNDABORT OFF
--GO
--ALTER DATABASE [CRDM] SET QUOTED_IDENTIFIER OFF
--GO
--ALTER DATABASE [CRDM] SET RECURSIVE_TRIGGERS OFF
--GO
--ALTER DATABASE [CRDM] SET  DISABLE_BROKER
--GO
--ALTER DATABASE [CRDM] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
--GO
--ALTER DATABASE [CRDM] SET DATE_CORRELATION_OPTIMIZATION OFF
--GO
--ALTER DATABASE [CRDM] SET TRUSTWORTHY OFF
--GO
--ALTER DATABASE [CRDM] SET ALLOW_SNAPSHOT_ISOLATION OFF
--GO
--ALTER DATABASE [CRDM] SET PARAMETERIZATION SIMPLE
--GO
--ALTER DATABASE [CRDM] SET READ_COMMITTED_SNAPSHOT OFF
--GO
--ALTER DATABASE [CRDM] SET HONOR_BROKER_PRIORITY OFF
--GO
--ALTER DATABASE [CRDM] SET RECOVERY SIMPLE
--GO
--ALTER DATABASE [CRDM] SET  MULTI_USER
--GO
--ALTER DATABASE [CRDM] SET PAGE_VERIFY CHECKSUM
--GO
--ALTER DATABASE [CRDM] SET DB_CHAINING OFF
--GO
--ALTER DATABASE [CRDM] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
--GO
--ALTER DATABASE [CRDM] SET TARGET_RECOVERY_TIME = 0 SECONDS
--GO
--ALTER DATABASE [CRDM] SET DELAYED_DURABILITY = DISABLED
--GO
USE [CRDM]
GO
/****** Object:  Table [dbo].[ChangeHistory]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChangeHistory](
	[Id] [int] NOT NULL,
	[Note] [nchar](10) NULL,
	[FormulaVersionId] [int] NULL,
	[EditBy] [nchar](10) NULL,
 CONSTRAINT [PK_ChangeHistory] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Client]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Client](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Client] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FormulaVersion]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FormulaVersion](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Code] [nchar](10) NULL,
	[ProjectId] [int] NOT NULL,
	[LastEdittedDate] [date] NULL,
	[CreatedDate] [date] NULL,
	[Status] [int] NULL,
	[CreateBy] [nchar](10) NULL,
	[VersionCodeBaseOn] [nchar](10) NULL,
	[OutsourceTestFile] [nchar](10) NULL,
 CONSTRAINT [PK_Formula Version] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FormulaVersionDetail]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FormulaVersionDetail](
	[FormulaVersionId] [int] NOT NULL,
	[MaterialSupplierId] [int] NOT NULL,
	[Percentage] [decimal](18, 0) NULL,
 CONSTRAINT [PK_FormulaVersionDetail] PRIMARY KEY CLUSTERED
(
	[FormulaVersionId] ASC,
	[MaterialSupplierId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Function]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Function](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NULL,
 CONSTRAINT [PK_Function] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Material]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Material](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Code] [nchar](10) NULL,
	[INCIName] [nvarchar](50) NULL,
	[SupplierLink] [varchar](max) NULL,
	[SpecificationLink] [varchar](max) NULL,
 CONSTRAINT [PK_Material] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MaterialFunction]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MaterialFunction](
	[FunctionId] [int] NOT NULL,
	[MaterialSupplierId] [int] NOT NULL,
 CONSTRAINT [PK_MaterialSupplierFunction] PRIMARY KEY CLUSTERED
(
	[FunctionId] ASC,
	[MaterialSupplierId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MaterialSupplier]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MaterialSupplier](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaterialId] [int] NOT NULL,
	[SupplierId] [int] NOT NULL,
	[Name] [varchar](250) NULL,
	[DDP] [decimal](18, 0) NULL,
	[Currency] [nchar](10) NULL,
	[Status] [int] NULL,
	[QualityControlFile] [varchar](max) NULL,
 CONSTRAINT [PK_MaterialDetail] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phase]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phase](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[FormulaVersionId] [int] NULL,
	[Description] [nvarchar](250) NULL,
	[OrderNum] [int] NULL,
 CONSTRAINT [PK_Phase] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhaseDetail]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhaseDetail](
	[PhaseId] [int] NOT NULL,
	[MaterialId] [int] NOT NULL,
 CONSTRAINT [PK_PhaseDetail] PRIMARY KEY CLUSTERED
(
	[PhaseId] ASC,
	[MaterialId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Project]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Project](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Product] [varchar](50) NULL,
	[Requirement] [nvarchar](250) NULL,
	[CreatedDate] [datetime] NULL,
	[Status] [int] NULL,
	[Deadline] [datetime] NULL,
	[ClientId] [int] NOT NULL,
 CONSTRAINT [PK_Project] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProjectAssign]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProjectAssign](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ProjectId] [int] NOT NULL,
	[UserId] [int] NOT NULL,
 CONSTRAINT [PK_ProjectAssign] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nchar](20) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Supplier]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Supplier](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Supplier] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TestCase]    Script Date: 2/23/2021 2:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TestCase](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[FormulaVersionId] [int] NOT NULL,
	[Status] [int] NULL,
	[Description] [nvarchar](250) NULL,
 CONSTRAINT [PK_TestCase] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 2/23/2021 2:17:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [varchar](50) NULL,
	[Password] [nvarchar](250) NULL,
	[Name] [nchar](10) NULL,
	[Phone] [char](10) NULL,
	[DateOfBirth] [date] NULL,
	[RoleId] [int] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChangeHistory]  WITH CHECK ADD  CONSTRAINT [FK_ChangeHistory_FormulaVersion] FOREIGN KEY([FormulaVersionId])
REFERENCES [dbo].[FormulaVersion] ([Id])
GO
ALTER TABLE [dbo].[ChangeHistory] CHECK CONSTRAINT [FK_ChangeHistory_FormulaVersion]
GO
ALTER TABLE [dbo].[FormulaVersion]  WITH CHECK ADD  CONSTRAINT [FK_FormulaVersion_Project] FOREIGN KEY([ProjectId])
REFERENCES [dbo].[Project] ([Id])
GO
ALTER TABLE [dbo].[FormulaVersion] CHECK CONSTRAINT [FK_FormulaVersion_Project]
GO
ALTER TABLE [dbo].[FormulaVersionDetail]  WITH CHECK ADD  CONSTRAINT [FK_Formula Version Detail_Formula Version] FOREIGN KEY([FormulaVersionId])
REFERENCES [dbo].[FormulaVersion] ([Id])
GO
ALTER TABLE [dbo].[FormulaVersionDetail] CHECK CONSTRAINT [FK_Formula Version Detail_Formula Version]
GO
ALTER TABLE [dbo].[FormulaVersionDetail]  WITH CHECK ADD  CONSTRAINT [FK_FormulaVersionDetail_MaterialSupplier] FOREIGN KEY([MaterialSupplierId])
REFERENCES [dbo].[MaterialSupplier] ([Id])
GO
ALTER TABLE [dbo].[FormulaVersionDetail] CHECK CONSTRAINT [FK_FormulaVersionDetail_MaterialSupplier]
GO
ALTER TABLE [dbo].[MaterialFunction]  WITH CHECK ADD  CONSTRAINT [FK_MaterialSupplierFunction_Function] FOREIGN KEY([FunctionId])
REFERENCES [dbo].[Function] ([Id])
GO
ALTER TABLE [dbo].[MaterialFunction] CHECK CONSTRAINT [FK_MaterialSupplierFunction_Function]
GO
ALTER TABLE [dbo].[MaterialFunction]  WITH CHECK ADD  CONSTRAINT [FK_MaterialSupplierFunction_Material] FOREIGN KEY([MaterialSupplierId])
REFERENCES [dbo].[Material] ([Id])
GO
ALTER TABLE [dbo].[MaterialFunction] CHECK CONSTRAINT [FK_MaterialSupplierFunction_Material]
GO
ALTER TABLE [dbo].[MaterialSupplier]  WITH CHECK ADD  CONSTRAINT [FK_MaterialSupplier_Material] FOREIGN KEY([MaterialId])
REFERENCES [dbo].[Material] ([Id])
GO
ALTER TABLE [dbo].[MaterialSupplier] CHECK CONSTRAINT [FK_MaterialSupplier_Material]
GO
ALTER TABLE [dbo].[MaterialSupplier]  WITH CHECK ADD  CONSTRAINT [FK_MaterialSupplier_Supplier] FOREIGN KEY([SupplierId])
REFERENCES [dbo].[Supplier] ([Id])
GO
ALTER TABLE [dbo].[MaterialSupplier] CHECK CONSTRAINT [FK_MaterialSupplier_Supplier]
GO
ALTER TABLE [dbo].[Phase]  WITH CHECK ADD  CONSTRAINT [FK_Phase_FormulaVersion] FOREIGN KEY([FormulaVersionId])
REFERENCES [dbo].[FormulaVersion] ([Id])
GO
ALTER TABLE [dbo].[Phase] CHECK CONSTRAINT [FK_Phase_FormulaVersion]
GO
ALTER TABLE [dbo].[PhaseDetail]  WITH CHECK ADD  CONSTRAINT [FK_PhaseDetail_Material1] FOREIGN KEY([MaterialId])
REFERENCES [dbo].[Material] ([Id])
GO
ALTER TABLE [dbo].[PhaseDetail] CHECK CONSTRAINT [FK_PhaseDetail_Material1]
GO
ALTER TABLE [dbo].[PhaseDetail]  WITH CHECK ADD  CONSTRAINT [FK_PhaseDetail_Phase] FOREIGN KEY([PhaseId])
REFERENCES [dbo].[Phase] ([Id])
GO
ALTER TABLE [dbo].[PhaseDetail] CHECK CONSTRAINT [FK_PhaseDetail_Phase]
GO
ALTER TABLE [dbo].[Project]  WITH CHECK ADD  CONSTRAINT [FK_Project_Client] FOREIGN KEY([ClientId])
REFERENCES [dbo].[Client] ([Id])
GO
ALTER TABLE [dbo].[Project] CHECK CONSTRAINT [FK_Project_Client]
GO
ALTER TABLE [dbo].[ProjectAssign]  WITH CHECK ADD  CONSTRAINT [FK_ProjectAssign_Project] FOREIGN KEY([ProjectId])
REFERENCES [dbo].[Project] ([Id])
GO
ALTER TABLE [dbo].[ProjectAssign] CHECK CONSTRAINT [FK_ProjectAssign_Project]
GO
ALTER TABLE [dbo].[ProjectAssign]  WITH CHECK ADD  CONSTRAINT [FK_ProjectAssign_User] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[ProjectAssign] CHECK CONSTRAINT [FK_ProjectAssign_User]
GO
ALTER TABLE [dbo].[TestCase]  WITH CHECK ADD  CONSTRAINT [FK_TestCase_FormulaVersion] FOREIGN KEY([FormulaVersionId])
REFERENCES [dbo].[FormulaVersion] ([Id])
GO
ALTER TABLE [dbo].[TestCase] CHECK CONSTRAINT [FK_TestCase_FormulaVersion]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Role] ([Id])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Role]
GO
USE [master]
GO
ALTER DATABASE [CRDM] SET  READ_WRITE
GO

