import sys

A = int(sys.stdin.readline().rstrip())
B = int(sys.stdin.readline().rstrip())
C = int(sys.stdin.readline().rstrip())

N = A * B * C 
sys_in = [0,0,0,0,0,0,0,0,0,0]

for item in list(str(N)):
    sys_in[int(item)] += 1

for item in sys_in:
    print(item)