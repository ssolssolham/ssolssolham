#s = "RLRRLLRLRL"
#s = "RLLLLRRRLR"
s = "LLLLRRRR"
class Solution:
    def balancedStringSplit(self, s: str) -> int:
        cntR = 0
        cntL = 0
        result = 0

        for ch in list(s):

            if ch == 'R':
                cntR += 1
            elif ch == 'L':
                cntL += 1

            if cntL != 0 and cntR != 0 and cntR == cntL :
                result += 1

        print(result)



Solution.balancedStringSplit(Solution,s)
