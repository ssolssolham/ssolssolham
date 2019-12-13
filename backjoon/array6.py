import sys
# 과목의 개수 : N
N = int(sys.stdin.readline().rstrip())
# 과목 점수들의 집합배열
A = sys.stdin.readline().rstrip().split()

# Max 구하기, M이 최대값
M = 0
# 총합 구하기, SUMR
SUMR = 0

# A중 M구하기
for item in A:
    SUMR += int(item)
    if M < int(item):
        M = int(item)
# 모든 점수를 점수/M * 100
# 1 ~ 10 ((점수) / M * 100)
# (총합 / M) * 100
print((SUMR / M) * 100 / N)

