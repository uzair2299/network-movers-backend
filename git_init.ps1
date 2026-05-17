# git_init.ps1
# Script to initialize git, add remote, commit all files, and set up branches

$remoteUrl = "https://github.com/uzair2299/network-movers-backend.git"

# 1. Create README.md if it doesn't exist
if (!(Test-Path "README.md")) {
    Set-Content -Path "README.md" -Value "# network-movers-backend`n`nSpring Boot Enterprise Moving Services platform." -Encoding utf8
}

Write-Host "Initializing git repository..." -ForegroundColor Green
git init

# Configure dummy local email/name if not already configured globally to prevent commit failures
$globalName = git config --global user.name
$globalEmail = git config --global user.email

if ([string]::IsNullOrEmpty($globalName)) {
    git config --local user.name "uzair2299"
}
if ([string]::IsNullOrEmpty($globalEmail)) {
    git config --local user.local "uzair2299@github.com"
}

Write-Host "Adding files to git index..." -ForegroundColor Green
git add README.md
# Also add the rest of the project files so we commit the completed architecture!
git add pom.xml src/ docs/ devops/ database/ .gitkeep -ErrorAction SilentlyContinue

Write-Host "Creating initial commit..." -ForegroundColor Green
git commit -m "first commit"

Write-Host "Setting main branch..." -ForegroundColor Green
git branch -M main

# Check if origin already exists
$existingRemote = git remote get-url origin 2>$null
if ([string]::IsNullOrEmpty($existingRemote)) {
    git remote add origin $remoteUrl
} else {
    git remote set-url origin $remoteUrl
}

Write-Host "Git initialization and local commit completed successfully!" -ForegroundColor Green
