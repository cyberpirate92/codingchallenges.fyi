# Challenge: 01 - Write Your Own wc Tool

[Challenge Link](https://codingchallenges.fyi/challenges/challenge-wc)

### Steps to run
1. Build the project (This project is created using IntelliJ IDEA, it's best to use the same as the project has build configurations for it)
2. The `scripts/ccwc.sh` script can be used to run the program once it's built.

```bash
  scripts/ccwc.sh test_data/*.txt
```
---
### Large files
Some 100MB+ files are available as a tar file in the test_data folder. The following script can be used to extract them.

```bash
  bash scripts/extract_large_files.sh
```
---