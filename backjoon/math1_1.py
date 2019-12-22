import sys
import math
N = sys.stdin.readline().rstrip().split()
fixed_cost = int(N[0])
variable_cost = int(N[1])
price = int(N[2])
# cnt * price = fixed_cost + cnt * variable_cost
# cnt(price - variable_cost) = fixed_cost

# 0으로 나눌수는 없다. 그러면..
if price == variable_cost:
    print(-1)
else:
    result = int(fixed_cost / (price - variable_cost) + 1)
    if result < 0:
        print(-1)
    else:
        print(result)

