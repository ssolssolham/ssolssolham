import sys

# 횟수 N
N = int(sys.stdin.readline().rstrip())

for j in range(0,N):
    # 한 줄마다 개수와 점수..
    A = sys.stdin.readline().rstrip().split()
    # 각 배열의 가장 앞은 수의 개수
    M = int(A[0])
    # 평균 구하기, AVERG
    # 그러기 위해 총합 SUMR
    AVERG = 0
    SUMR = 0

    for i in range(1,M+1):
        SUMR += int(A[i])

    AVERG = (SUMR / M)

    # 평균이 넘는 수 구하기
    gt_num = 0
    for i in range(1,M+1):
        if AVERG < int(A[i]):
            gt_num += 1
    result = gt_num / M * 100
    print("%0.3f%%" % result)
        
    