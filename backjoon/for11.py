import sys
N, X = map(int, sys.stdin.readline().rstrip().split())
A = sys.stdin.readline().rstrip().split()
result = ''
first = True

for item in A:
    if X > int(item) :
        if first :
            result += item
            first = False
        else :
            result += (' ' + item)

print(result)
