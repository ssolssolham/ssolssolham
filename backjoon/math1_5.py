import sys
import math

N = sys.stdin.readline().rstrip().split()
A = int(N[0])
B = int(N[1])
C = int(N[2])
days = 0

days = math.ceil((C - B) / (A - B))
print(days)