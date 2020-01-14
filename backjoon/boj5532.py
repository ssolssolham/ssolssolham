import sys
import math

L = float(sys.stdin.readline().rstrip())
A = float(sys.stdin.readline().rstrip())
B = float(sys.stdin.readline().rstrip())
C = float(sys.stdin.readline().rstrip())
D = float(sys.stdin.readline().rstrip())

per_kor = A / C
per_math = B / D

if per_kor >= per_math:
    print(int(L - math.ceil(per_kor)))
else :
    print(int(L - math.ceil(per_math)))