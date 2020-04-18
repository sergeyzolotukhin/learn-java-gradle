import subprocess
import logging as log
from pathlib import Path
import shutil
import sys

FORMAT = '%(asctime)23.23s [ %(levelname)10.10s ] %(filename)20.20s:%(lineno)4.4s - %(message)s'
log.basicConfig(stream=sys.stdout, level=log.INFO, format=FORMAT)

version = 'v0.0.1'
baseName = 'gof-a'
archiveExtension = 'zip'
dirName = f'{baseName}-{version}'
archiveName = f'{dirName}.{archiveExtension}'

dirPath = Path('.', 'build', f'{dirName}')
if dirPath.exists() and dirPath.is_dir():
    shutil.rmtree(dirPath)

zipPath = Path('.', 'build', f'{archiveName}')
if zipPath.exists() and zipPath.is_fifo():
    shutil.rmtree(zipPath)

archive = subprocess.run(["git", "archive", f'--output=./build/{archiveName}', f'{version}', "./pattern-gof-adapter"])

unzip = subprocess.run(["7z", "x", f'build/{archiveName}', f'-obuild//{dirName}'], capture_output=True)

zipPath = Path('.', 'build', f'{archiveName}')
if zipPath.exists() and zipPath.is_fifo():
    shutil.rmtree(zipPath)

log.info(f'Checkout data from git with tag "{version}" to path: [./build/{dirName}/pattern-gof-adapter]')
