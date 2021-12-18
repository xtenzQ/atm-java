# KMS Lighthouse Back End Developer position test task

## Task

Create simple Cash Machine emulator.

_Requirements_:
- [x] stand-alone console I/O Java program using any JDK;
- [x] follow OOP paradigm;
- [x] program should be easily extendable;
- [x] provide logging to the output file;
- [x] divide program modules into packages;
- [x] generate JavaDocs;
- [x] write JUnit tests for public methods;
- [x] make Ant `build.xml`;
- [ ] make functional test.

Input commands:
1. Cash deposit 
```Bash
Format: + <currency> <denomination> <amount>
Reply: OK on success, ERROR on validation fail
Example:
+ RUB 500 20
OK
```

2. Cash withdraw
```Bash
Format: - <currency> <amount>
Reply: OK on success, ERROR if amount unavailable
Example:
+ RUB 500 20
OK
```

## 1. Problem decomposition

1. Cash machine - provides input/output and runs the cash device
2. Cash device - responsible for cash operations
3. Command executor

