ipt = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
answer = {}
result = []

for item in ipt:
    cnt = item.split(' ')[0]
    site = item.split(' ')[1]
    site = ' ' + site
    idx = 1

    while idx != 0:
        site = site[idx:]
        idx = site.find('.') + 1
        print(site)
        if answer.get(site) == None:
            answer[site] = int(cnt)
        else:
            answer[site] += int(cnt)

for key, value in answer.items():
    result.append(str(value) + ' ' + key)

print(result)




#sub_domain = site.split('.')

#sub_domain.reverse()
#for i in range(0,len(sub_domain)):

