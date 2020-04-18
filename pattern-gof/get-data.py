import subprocess
import logging as log
import shutil
import sys
import os

from pathlib import Path

FORMAT = '%(asctime)23.23s [ %(levelname)10.10s ] %(filename)20.20s:%(lineno)4.4s - %(message)s'
log.basicConfig(stream=sys.stdout, level=log.DEBUG, format=FORMAT)

version = 'v0.0.1'
baseName = 'gof-a'
archiveExtension = 'zip'
dirName = f'{baseName}-{version}'
archiveName = f'{dirName}.{archiveExtension}'
moduleDir = 'pattern-gof'

dirPath = Path('.', 'build', f'{dirName}')
if dirPath.exists() and dirPath.is_dir():
    shutil.rmtree(dirPath)

zipPath = Path('.', 'build', archiveName)
if zipPath.exists() and zipPath.is_file():
    os.remove(zipPath)

archive = subprocess.run(["git", "archive", f'--output=./build/{archiveName}', f'{version}', "./pattern-gof-adapter"])

unzip = subprocess.run(["7z", "x", f'build/{archiveName}', f'-obuild//{dirName}'], capture_output=True)

if zipPath.exists() and zipPath.is_file():
    os.remove(zipPath)

log.info(f'Checkout data from git with tag "{version}" to path: [./build/{dirName}/pattern-gof-adapter]')
