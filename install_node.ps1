Write-Host "Downloading Node.js installer..."
$nodeUrl = "https://nodejs.org/dist/v20.11.0/node-v20.11.0-x64.msi"
$installerPath = "$env:TEMP\node.msi"

Invoke-WebRequest -Uri $nodeUrl -OutFile $installerPath

Write-Host "Installing Node.js..."
Start-Process msiexec.exe -Wait -ArgumentList "/i $installerPath /quiet"

Write-Host "Node.js installation complete!"
Write-Host "Please restart your terminal to use Node.js and npm."
