import sys

N = sys.stdin.readline().rstrip()
A = sys.stdin.readline().rstrip().split()

min_num =  1000000
max_num = -1000000
for item in A:
    if min_num > int(item):
        min_num = int(item)
    if max_num < int(item):
        max_num = int(item)
        
print(str(min_num) + ' ' + str(max_num))
