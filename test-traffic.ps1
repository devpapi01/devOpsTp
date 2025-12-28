for ($i=1; $i -le 100; $i++) {
Write-Host "Requête $i"
Invoke-WebRequest -Uri http://localhost:8080/actuator/health -UseBasicParsing
Start-Sleep -Milliseconds 500
}