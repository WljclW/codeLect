# ======================================
# 文件名前缀批量处理脚本（增强版）
# ======================================

# 设置当前目录为脚本所在目录
Set-Location $PSScriptRoot

Write-Host "当前工作目录：" -ForegroundColor Cyan
Write-Host $PSScriptRoot
Write-Host ""

# -------------------------------
# 选择操作
# -------------------------------
Write-Host "=============================="
Write-Host "请选择操作："
Write-Host "1 - 添加前缀"
Write-Host "2 - 删除前缀"
Write-Host "=============================="

$Action = Read-Host "请输入数字 (1 / 2)"

if ($Action -ne "1" -and $Action -ne "2") {
    Write-Host "无效选择，程序退出" -ForegroundColor Red
    Pause
    exit
}

# -------------------------------
# 输入前缀
# -------------------------------
$Prefix = Read-Host "请输入前缀"

if ([string]::IsNullOrWhiteSpace($Prefix)) {
    Write-Host "前缀不能为空" -ForegroundColor Red
    Pause
    exit
}

# -------------------------------
# 获取文件（排除 .bat / .ps1）
# -------------------------------
$Files = Get-ChildItem -File | Where-Object {
    $_.Extension -notin @(".bat", ".ps1")
}

if ($Files.Count -eq 0) {
    Write-Host "当前目录下没有可处理的文件（已排除 .bat / .ps1）" -ForegroundColor Yellow
    Pause
    exit
}

# ==================================================
# 添加前缀
# ==================================================
if ($Action -eq "1") {

    Write-Host "正在添加前缀..." -ForegroundColor Green

    foreach ($File in $Files) {
        Rename-Item -Path $File.FullName -NewName ($Prefix + $File.Name)
    }

    Write-Host "前缀添加完成！" -ForegroundColor Cyan
    Pause
    exit
}

# ==================================================
# 删除前缀（关键逻辑：先校验，再执行）
# ==================================================

Write-Host "正在校验文件前缀..." -ForegroundColor Yellow

# 找出没有指定前缀的文件
$InvalidFiles = $Files | Where-Object {
    -not $_.Name.StartsWith($Prefix)
}

if ($InvalidFiles.Count -gt 0) {

    Write-Host ""
    Write-Host "❌ 错误信息：" -ForegroundColor Red
    Write-Host "请确认输入的前缀信息是不是正确，部分文件没有指定前缀" -ForegroundColor Red
    Write-Host ""

    Write-Host "❌ 提示信息：" -ForegroundColor Yellow
    Write-Host "以下文件不包含前缀 [$Prefix]：" -ForegroundColor Yellow
    foreach ($File in $InvalidFiles) {
        Write-Host " - $($File.Name)"
    }

    Write-Host ""
    Write-Host "已终止操作，未对任何文件做修改。" -ForegroundColor Red
    Pause
    exit
}

# ---- 所有文件都校验通过，开始真正删除 ----
Write-Host "校验通过，正在删除前缀..." -ForegroundColor Green

foreach ($File in $Files) {
    $NewName = $File.Name.Substring($Prefix.Length)
    Rename-Item -Path $File.FullName -NewName $NewName
}

Write-Host ""
Write-Host "前缀删除完成！" -ForegroundColor Cyan
Pause
