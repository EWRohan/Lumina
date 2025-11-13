@echo off
echo =========================================
echo 🔧 Cleaning Gradle Build and Cache Files...
echo =========================================

:: Kill running Gradle or Java processes
taskkill /F /IM java.exe /T >nul 2>&1
taskkill /F /IM gradle.exe /T >nul 2>&1

:: Delete build and Gradle cache directories
echo Deleting build folders...
rmdir /s /q .\app\build
rmdir /s /q .gradle

:: Optional: also delete intermediate build files
rmdir /s /q .\build
rmdir /s /q .\app\.cxx

echo ✅ Clean complete! You can now rebuild your project safely.
pause
