USE [master]
GO

/****** 1. TAO DATABASE (Neu chua co) ******/
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'xekhach')
BEGIN
    CREATE DATABASE [xekhach]
END
GO

USE [xekhach]
GO

/****** 2. TAO BANG (TABLES) ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Bang: Xe
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[xe]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[xe](
	[xeid] [int] IDENTITY(1,1) NOT NULL,
	[tenxe] [nvarchar](100) NOT NULL,
	[bienso] [varchar](20) NULL,
	[tongghe] [int] NULL,
	[loaixe] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED ([xeid] ASC)
)
END
GO

-- Bang: Tuyen Xe
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tuyenxe]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[tuyenxe](
	[tuyenid] [int] IDENTITY(1,1) NOT NULL,
	[khoihanh] [nvarchar](100) NOT NULL,
	[diemden] [nvarchar](100) NOT NULL,
	[khoangcach] [float] NULL,
	[thoigiandichuyen] [int] NULL,
PRIMARY KEY CLUSTERED ([tuyenid] ASC)
)
END
GO

-- Bang: Nguoi Dung
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[nguoidung]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[nguoidung](
	[nguoidungid] [int] IDENTITY(1,1) NOT NULL,
	[hoten] [nvarchar](100) NOT NULL,
	[ngaysinh] [date] NULL,
	[sdt] [varchar](15) NOT NULL,
	[email] [varchar](100) NULL,
	[taikhoan] [varchar](50) NOT NULL,
	[matkhau] [varchar](50) NOT NULL,
	[vaitro] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED ([nguoidungid] ASC)
)
END
GO

-- Bang: Lich Trinh
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[lichtrinh]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[lichtrinh](
	[lichid] [int] IDENTITY(1,1) NOT NULL,
	[xeid] [int] NOT NULL,
	[tuyenid] [int] NOT NULL,
	[thoigiankhoihanh] [datetime] NOT NULL,
	[thoigianden] [datetime] NOT NULL,
	[soghetrong] [int] NULL,
	[giave] [decimal](18, 0) NULL,
PRIMARY KEY CLUSTERED ([lichid] ASC)
)
END
GO

-- Bang: Ve Xe
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[vexe]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[vexe](
	[veid] [int] IDENTITY(1,1) NOT NULL,
	[nguoidungid] [int] NOT NULL,
	[lichid] [int] NOT NULL,
	[vitrighe] [varchar](50) NOT NULL,
	[thoigiandat] [datetime] NULL,
	[trangthai] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED ([veid] ASC)
)
END
GO

/****** 3. TAO RANG BUOC (CONSTRAINTS & KEYS) ******/

-- Rang buoc UNIQUE (Khong trung lap)
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name='UQ_sdt' AND object_id = OBJECT_ID('dbo.nguoidung'))
BEGIN
    ALTER TABLE [dbo].[nguoidung] ADD CONSTRAINT [UQ_sdt] UNIQUE NONCLUSTERED ([sdt] ASC)
END

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name='UQ_taikhoan' AND object_id = OBJECT_ID('dbo.nguoidung'))
BEGIN
    ALTER TABLE [dbo].[nguoidung] ADD CONSTRAINT [UQ_taikhoan] UNIQUE NONCLUSTERED ([taikhoan] ASC)
END

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name='uq_ghe_lichtrinh' AND object_id = OBJECT_ID('dbo.vexe'))
BEGIN
    ALTER TABLE [dbo].[vexe] ADD CONSTRAINT [uq_ghe_lichtrinh] UNIQUE NONCLUSTERED ([lichid] ASC, [vitrighe] ASC)
END

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name='UQ_bienso' AND object_id = OBJECT_ID('dbo.xe'))
BEGIN
    ALTER TABLE [dbo].[xe] ADD CONSTRAINT [UQ_bienso] UNIQUE NONCLUSTERED ([bienso] ASC)
END
GO

-- Gia tri mac dinh (DEFAULT)
ALTER TABLE [dbo].[lichtrinh] ADD DEFAULT ((0)) FOR [giave]
GO
ALTER TABLE [dbo].[nguoidung] ADD DEFAULT ('khachhang') FOR [vaitro]
GO
ALTER TABLE [dbo].[vexe] ADD DEFAULT (getdate()) FOR [thoigiandat]
GO
ALTER TABLE [dbo].[vexe] ADD DEFAULT (N'đã đặt') FOR [trangthai]
GO
ALTER TABLE [dbo].[xe] ADD CHECK (([tongghe]>(0)))
GO

-- Khoa ngoai (FOREIGN KEYS)
ALTER TABLE [dbo].[lichtrinh] WITH CHECK ADD FOREIGN KEY([tuyenid]) REFERENCES [dbo].[tuyenxe] ([tuyenid])
GO
ALTER TABLE [dbo].[lichtrinh] WITH CHECK ADD FOREIGN KEY([xeid]) REFERENCES [dbo].[xe] ([xeid])
GO
ALTER TABLE [dbo].[vexe] WITH CHECK ADD FOREIGN KEY([lichid]) REFERENCES [dbo].[lichtrinh] ([lichid])
GO
ALTER TABLE [dbo].[vexe] WITH CHECK ADD FOREIGN KEY([nguoidungid]) REFERENCES [dbo].[nguoidung] ([nguoidungid])
GO
