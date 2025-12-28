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

// dữ liệu bảng dbo.xe
    INSERT INTO xe (tenxe, bienso, tongghe, loaixe)
    VALUES
    -- Limousine 16 chỗ
    (N'Xe Limousine 01', N'29A-10001', 16, N'Limousine'),
    (N'Xe Limousine 02', N'29A-10002', 16, N'Limousine'),
    (N'Xe Limousine 03', N'30B-10003', 16, N'Limousine'),
    (N'Xe Limousine 04', N'30B-10004', 16, N'Limousine'),
    (N'Xe Limousine 05', N'51C-10005', 16, N'Limousine'),

    -- Giường nằm 30 chỗ
    (N'Xe Giường Nằm 01', N'43A-20001', 30, N'Giường nằm'),
    (N'Xe Giường Nằm 02', N'43A-20002', 30, N'Giường nằm'),
    (N'Xe Giường Nằm 03', N'92B-20003', 30, N'Giường nằm'),
    (N'Xe Giường Nằm 04', N'92B-20004', 30, N'Giường nằm'),
    (N'Xe Giường Nằm 05', N'29A-20005', 30, N'Giường nằm');


// dữ liệu bảng dbo.tuyenxe
INSERT INTO tuyenxe (khoihanh, diemden, khoangcach, thoigiandichuyen)
VALUES
-- Hà Nội → Hải Phòng
(N'Hà Nội', N'Hải Phòng', 120.0, 150),
(N'Hà Nội', N'Hải Phòng', 120.0, 150),
(N'Hà Nội', N'Hải Phòng', 120.0, 150),
(N'Hà Nội', N'Hải Phòng', 120.0, 150),

-- Hà Nội → Thanh Hóa
(N'Hà Nội', N'Thanh Hóa', 160.0, 180),
(N'Hà Nội', N'Thanh Hóa', 160.0, 180),
(N'Hà Nội', N'Thanh Hóa', 160.0, 180),

-- Hà Nội → Nam Định
(N'Hà Nội', N'Nam Định', 90.0, 120),
(N'Hà Nội', N'Nam Định', 90.0, 120),
(N'Hà Nội', N'Nam Định', 90.0, 120);


// dữ liệu bảng dbo.lichtrinh
INSERT INTO lichtrinh
(xeid, tuyenid, thoigiankhoihanh, thoigianden, soghetrong, giave)
VALUES
-- Hà Nội → Hải Phòng
(1, 1, '2025-01-10 07:00:00', '2025-01-10 09:30:00', 16, 150000),
(2, 1, '2025-01-10 10:00:00', '2025-01-10 12:30:00', 16, 150000),
(6, 1, '2025-01-10 19:00:00', '2025-01-10 22:30:00', 30, 180000),

-- Hà Nội → Thanh Hóa
(3, 2, '2025-01-11 06:00:00', '2025-01-11 09:00:00', 16, 200000),
(4, 2, '2025-01-11 13:00:00', '2025-01-11 16:00:00', 16, 200000),
(7, 2, '2025-01-11 22:00:00', '2025-01-12 01:00:00', 30, 230000),

-- Hà Nội → Nam Định
(5, 3, '2025-01-12 07:30:00', '2025-01-12 09:30:00', 16, 120000),
(8, 3, '2025-01-12 14:00:00', '2025-01-12 16:00:00', 30, 140000),
(9, 3, '2025-01-12 18:30:00', '2025-01-12 20:30:00', 30, 140000),
(10, 3, '2025-01-13 06:00:00', '2025-01-13 08:00:00', 16, 120000);

// dữ liệu bảng dbo.nguoidung
INSERT INTO nguoidung
(hoten, ngaysinh, sdt, email, taikhoan, matkhau, vaitro)
VALUES
(N'Nguyễn Văn A', '1995-03-12', '0901234567', 'a@gmail.com', 'nguyenvana', '123456', N'KHÁCH'),
(N'Trần Thị B', '1998-07-25', '0912345678', 'b@gmail.com', 'tranthib', '123456', N'KHÁCH'),
(N'Lê Văn C', '1990-11-05', '0923456789', 'c@gmail.com', 'levanc', '123456', N'KHÁCH'),
(N'Phạm Thị D', '1997-02-18', '0934567890', 'd@gmail.com', 'phamthid', '123456', N'KHÁCH'),
(N'Hoàng Văn E', '1988-09-30', '0945678901', 'e@gmail.com', 'hoangvane', '123456', N'ADMIN');

// dữ liệu bảng dbo.vexe
INSERT INTO vexe
(nguoidungid, lichid, vitrighe, thoigiandat, trangthai)
VALUES
(1, 1,  N'A1',  GETDATE(), N'ĐÃ ĐẶT'),
(2, 1,  N'A5',  GETDATE(), N'ĐÃ ĐẶT'),
(3, 2,  N'A8',  GETDATE(), N'ĐÃ ĐẶT'),
(4, 4,  N'A12', GETDATE(), N'ĐÃ ĐẶT'),
(5, 5,  N'A6',  GETDATE(), N'ĐÃ ĐẶT'),
(1, 3,  N'B10', GETDATE(), N'ĐÃ ĐẶT'),
(2, 9,  N'B15', GETDATE(), N'ĐÃ ĐẶT'),
(3, 6,  N'B24', GETDATE(), N'ĐÃ ĐẶT'),
(4, 10, N'B4',  GETDATE(), N'ĐÃ ĐẶT'),
(5, 8,  N'B18', GETDATE(), N'ĐÃ ĐẶT');
