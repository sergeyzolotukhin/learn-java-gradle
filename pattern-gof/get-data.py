import subprocess

archive = subprocess.run(["git", "archive", "--output=./build/gof-a-v0.0.1.zip", "v0.0.1", "./pattern-gof-adapter"])
print("Git archive was created, Status code: %d" % archive.returncode)


