import sys

while True:
    line = sys.stdin.readline()
    if line == "" or len(line) == 1:
        break
    A, B = map(int, line.split())
    print(A+B)