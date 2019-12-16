from Content3 import Content
from Content3 import Website
import requests
from bs4 import BeautifulSoup
import re

class Crawler:
    
    def __init__(self,site):
        self.site = site
        self.visited = []
        
    def getPage(self, url):
        try:
            req = requests.get(url)
        except requests.exceptions.RequestException:
            return None
        return BeautifulSoup(req.text,'html.parser')
    
    def safeGet(self,pageObj,selector):
        """
        BeautifulSoup 객체와 선택자를 받아 콘텐츠 문자열을 추출하는 함수
        주어진 선택지로 검색된 결과가 없다면 빈 문자열을 반환합니다.
        """
        
        selectedElems = pageObj.select(selector)
        if selectedElems is not None and len(selectedElems) > 0:
            return '\n'.join([elem.get_text() for elem in selectedElems])
        return ''
    
    def parse(self,url):
        """
        URL을 받아 콘텐츠를 추출합니다
        """
        bs = self.getPage(url)
        if bs is not None:
            title = self.safeGet(bs, self.site.titleTag)
            body = self.safeGet(bs, self.site.bodyTag)
            if title != '' and body != '':
                content = Content(url,title,body)
                content.print()
    
    def crawl(self):
        """
        사이트 홈페이지에서 페이지를 가져옵니다.
        """
        bs = self.getPage(self.site.url)
        targetPages = bs.findAll('a', href=re.compile(self.site.targetPattern))
        for targetPage in targetPages:
            targetPage = targetPage.attrs['href']
            if targetPage not in self.visited:
                self.visited.append(targetPage)
                if not self.site.absoluteUrl:
                    targetPage = '{}{}'.format(self.site.url, targetPage)
                self.parse(targetPage)
                
                
reuters = Website('Reuters','https://www.reuters.com','^(/article/)',False,'h1','div.StandardArticleBody_body')
crawler = Crawler(reuters)
crawler.crawl()