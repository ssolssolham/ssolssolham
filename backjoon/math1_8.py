import sys
import math
N = int(sys.stdin.readline().rstrip())

for i in range(0,N):
    tmp = sys.stdin.readline().rstrip().split()
    diff = int(tmp[1]) - int(tmp[0])
    
    j = 1
    while (j ** 2) <= diff:
        j += 1 
    j -= 1
    # 최고속도는 j
    
    # 5면 remain 1
    # 6면 remain 2
    remain = diff - (j ** 2)
    
    # 1 / 4 1
    # 2 / 4 1
    remain = math.ceil(remain / j)
    
    print(j * 2 - 1 + remain)

'''
1  1    1   1
2  11   2
3  111  3
4  121  3   2
5  1211 4
6  1221 4
7  12211    5
8  12221    5
9  12321    5   3
10 123211   6
11 123221   6
12 123321   6

1 4 9 16 최대거리
1 3 5 7  워프 횟수(최대거리 / 2(버림) + 1)
1 2 3 4  최고 속도
'''



