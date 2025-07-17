# Add Maven to PATH
$env:Path += ";C:\Program Files\apache-maven-3.9.10\bin"

# Update PATH permanently
$env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";C:\Program Files\apache-maven-3.9.10\bin"
[System.Environment]::SetEnvironmentVariable("Path", $env:Path, "Machine")

Write-Host "Maven added to PATH successfully!" -ForegroundColor Green
Write-Host "You may need to restart your terminal for changes to take effect." -ForegroundColor Yellow

# Verify Maven installation
Write-Host "Verifying Maven installation..." -ForegroundColor Green
mvn --version
