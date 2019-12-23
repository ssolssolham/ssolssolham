import sys
N = int(sys.stdin.readline())

result = 1
num = 1
sumr = 1

# 10이면..
while 1:
    if N <= num:
        break
    else:
        num += (6 * result)
        sumr += num
        result += 1
print(result)