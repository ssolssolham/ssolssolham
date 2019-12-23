import time
import sys

start = time.time()
X = int(sys.stdin.readline().rstrip())
num = 1
sumr = 1
while 1:
    if X <= sumr:
        break
    else :
        num += 1
        sumr += num
A = sumr - X

if num % 2 == 0:
    print(str(num - A) + '/' + str(1 + A))
else:
    print(str(1 + A) + '/' + str(num - A))
print('time : ', time.time() - start)