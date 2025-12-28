@echo off
chcp 65001 >nul
setlocal EnableExtensions

:: ===== 在这里修改前缀 =====
set "PREFIX=测试前缀_"
:: ===========================

:: 切换到 bat 所在目录
cd /d "%~dp0"

echo 使用前缀：%PREFIX%
echo.

:: 遍历当前目录下的所有文件
for %%f in (*) do (
    if not "%%~nxf"=="%~nx0" (
        if not "%%~xf"=="" (
            ren "%%f" "%PREFIX%%%f"
        )
    )
)

echo 操作完成！
pause
