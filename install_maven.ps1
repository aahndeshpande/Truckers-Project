# Download Maven
$version = "3.9.6"
$downloadUrl = "https://dlcdn.apache.org/maven/maven-3/$version/binaries/apache-maven-$version-bin.zip"
$outputZip = "apache-maven-$version-bin.zip"
$outputDir = "apache-maven-$version"

# Create Maven directory
New-Item -ItemType Directory -Force -Path "C:\Maven"

# Download Maven
Invoke-WebRequest -Uri $downloadUrl -OutFile $outputZip

# Extract Maven
Expand-Archive -Path $outputZip -DestinationPath "C:\Maven" -Force

# Add Maven to PATH
$env:Path += ";C:\Maven\$outputDir\bin"

# Update PATH permanently
$env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";C:\Maven\$outputDir\bin"
[System.Environment]::SetEnvironmentVariable("Path", $env:Path, "Machine")

# Clean up
Remove-Item $outputZip

Write-Host "Maven installed successfully!" -ForegroundColor Green
Write-Host "You may need to restart your terminal for PATH changes to take effect." -ForegroundColor Yellow
