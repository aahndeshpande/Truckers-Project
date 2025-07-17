# Add Node.js to PATH
$env:Path += ";D:\software\Nodejs"

# Update PATH permanently
$env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";D:\software\Nodejs"
[System.Environment]::SetEnvironmentVariable("Path", $env:Path, "Machine")

Write-Host "Node.js added to PATH successfully!" -ForegroundColor Green
Write-Host "You may need to restart your terminal for changes to take effect." -ForegroundColor Yellow

# Verify Node.js installation
Write-Host "Verifying Node.js installation..." -ForegroundColor Green
node --version
