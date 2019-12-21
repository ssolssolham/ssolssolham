import sys

S = sys.stdin.readline().rstrip()
result = 0

for item in list(S):
    N = ord(item)
    # A ~ C
    if N >= 65 and N <= 67:
        result += 3
    # D ~ F
    elif N >= 68 and N <= 70:
        result += 4
    # G ~ I
    elif N >= 71 and N <= 73:
        result += 5
    # J ~ L
    elif N >= 74 and N <= 76:
        result += 6
    # M ~ O
    elif N >= 77 and N <= 79:
        result += 7
    # P ~ S
    elif N >= 80 and N <= 83:
        result += 8
    # T ~ U
    elif N >= 84 and N <= 86:
        result += 9
    # X ~ Z
    elif N >= 87 and N <= 90:
        result += 10
print(result)