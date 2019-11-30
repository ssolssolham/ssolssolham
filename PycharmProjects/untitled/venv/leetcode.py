Example 1:
Input:
["9001 discuss.leetcode.com"]
Output:
["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
Explanation:
We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.

Example 2:
Input:
["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output:
["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
Explanation:
We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.


# discuss.leetcode.com 도메인은 다양한 서브도메인이 있다.
# com, leetcode.com, discuss.leetcode.com
# discuss.leetcode.com과 같은 도메인을 방문할 때
# 우리는 leetcode.com과 com 또한 방문한다.

# count-paired domain :
# 앞의 번호는 몇번 방문했는지에 대한 것
# input의 구조
# - 방문숫자 :
# - 도메인 : google.mail.com

# divide : 병렬로 처리해야할 과정
# 도메인을 숫자와 분리
# 900, google.mail.com
# 도메인 내에서 .으로 분리
# google,mail,com
# 앞에서부터 만들어나가기
# 900 google.mail.com, 900 mail.com, 900 com

# conquer : 병합과정
# set에 도메인 종류들을 다 넣기
# pop을 이용해 도메인과 동일여부 확인 후 더하기

class Solution:
    def def subdomainVisits(self, cpdomains: List[str]) -> List[str]:
