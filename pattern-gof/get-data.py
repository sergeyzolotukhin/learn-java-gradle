import subprocess
import os
from pathlib import Path
import shutil

dirPath = Path('.', 'build', 'gof-a-v0.0.1')
if dirPath.exists() and dirPath.is_dir():
    shutil.rmtree(dirPath)
print("Target dir was removed")

zipPath = Path('.', 'build', 'gof-a-v0.0.1.zip')
if zipPath.exists() and zipPath.is_fifo():
    shutil.rmtree(zipPath)
print("Target zip was removed")

archive = subprocess.run(["git", "archive", "--output=./build/gof-a-v0.0.1.zip", "v0.0.1", "./pattern-gof-adapter"])
print("Git archive was created")

unzip = subprocess.run(["7z", "x", "build/gof-a-v0.0.1.zip", "-obuild//gof-a-v0.0.1"], capture_output=True)
print("Data was unarchived")

zipPath = Path('.', 'build', 'gof-a-v0.0.1.zip')
if zipPath.exists() and zipPath.is_fifo():
    shutil.rmtree(zipPath)
print("Target zip was removed")
