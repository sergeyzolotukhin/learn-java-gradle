There are four types of objects in Git’s internal storage.
    Commit objects,
    annotated tag objects,
    blobs and
    tree objects.

https://blog.thoughtram.io/git/2014/11/18/the-anatomy-of-a-git-commit.html

git cat-file -p 41f57206bb5e66db80c9dbb15a01e51d63a385a5
git cat-file -t 41f57206bb5e66db80c9dbb15a01e51d63a385a5

git hash-object -t blob ./docs/README.md
git ls-files -s ./docs/README.md

96b04d2a60577f0c97ef30da471ae13cf62dd0ce

git cat-file -t 962ba1577660ccc68643b794ddddd0912001e4ae

git cat-file -p 6a4610e08d629bcc8f890b457c40e086fa024e1d
git cat-file -p 9531a73a66fc6696d2b662320c68864cffd7b6fe
git cat-file -p 973d4ae9450015c8bdbb8a59cd5d75480a44df7c
git cat-file -p 96b04d2a60577f0c97ef30da471ae13cf62dd0ce

git show-index < pack-b69b02f1911736ccd859e367b17a699019864c82.idx

#### Improve your CI-CD-Workflow with Git-Notes
https://medium.com/digitalfrontiers/git-your-stuff-together-storing-test-reports-along-your-sources-with-git-notes-f5c8068dc981
https://dev.to/leehambley/effortlessly-maintain-a-high-quality-change-log-with-git-notes-4bm5