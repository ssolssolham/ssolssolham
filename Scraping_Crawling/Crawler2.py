from Content2 import Content
import requests
from bs4 import BeautifulSoup
from Content2 import Website

class Crawler:
    
    def getPage(self, url):
        try:
            req = requests.get(url)
        except requests.exceptions.RequestException:
            return None
        return BeautifulSoup(req.text,'html.parser')
    
    def safeGet(self, pageObj, selector):
        childObj = pageObj.select(selector)
        if childObj is not None and len(childObj) > 0:
            return childObj[0].get_text()
        return ''

    def search(self, topic, site):
        """
        주어진 검색어로 주어진 웹사이트를 검색해 결과 페이지를 모두 기록합니다.
        """
        bs = self.getPage(site.searchUrl + topic)
        searchResults = bs.select(site.resultListing)
        for result in searchResults:
            url = result.select(site.resultUrl)[0].attrs['href']
            # 상대 URL인지 절대 URL인지 확인합니다.
            if(site.absoluteUrl):
                bs = self.getPage(url)
            else:
                bs = self.getPage(site.url + url)
            if bs is None:
                print('Something was wrong with that page or URL. Skipping!')
                return
            title = self.safeGet(bs, site.titleTag)
            body = self.safeGet(bs, site.bodyTag)
            if title != '' and body != '':
                content = Content(topic, url, title, body)
                content.print()
    
crawler = Crawler()
    
siteData = [
    ['0\'Reilly Media',
        'http://oreilly.com',
        'https://ssearch.oreilly.com?q=',
        'article.product-result',
        'p.title a',
        True,
        'h1',
        'section#product-description'],
    ['Reuters', 'http://reuter.com',
        'http://www.reuters.com/search/news?blob=',
        'div.search-result-content',
        'h3.search-result-title a',
        False,
        'h1',
        'div.StandardArticleBody_body_1gnLA'],
    ['Brookings', 'http://www.brookings.edu',
        'https://www.brookings.edu/search/?s=',
        'div.list-content article',
        'h4.title a',
        True,
        'h1',
        'div.post-body']
]
 
print(len(siteData))
print(len(siteData[0]))
print(len(siteData[1]))
print(len(siteData[2]))
sites = []
for row in siteData:
    sites.append(Website(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]))

topics = ['python', 'data science']
for topic in topics:
    print('GETTING INFO ABOUT: ' + topic)
    for targetSite in sites:
        crawler.search(topic, targetSite)