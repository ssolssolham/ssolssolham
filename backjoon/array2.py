import sys

max_num = 0
order = 1
target = 0

while True:
    N = sys.stdin.readline().rstrip()
    if len(N) == 0:
        break
    else:
        if int(N) > max_num:
            max_num = int(N)
            target = order
        order += 1

print(max_num)
print(target)