Start-Process java -ArgumentList '-jar', 'registerdiscover.jar'
Start-Process java -ArgumentList '-jar', 'gateway.jar'
Start-Process java -ArgumentList '-jar', 'servicefactory.jar'
Write-Host "The infrastructure is starting it may take a while... Accessable under http://localhost:8761/"