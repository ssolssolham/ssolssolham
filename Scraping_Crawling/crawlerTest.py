from Content123 import Website
from Crawler import Crawler
crawler = Crawler()

siteData = [
    ['0\'Reilly Media', 'http://oreilly.com',
     'h1','section#product-description'],
    ['Reuters','http://reuters.com',
     'h1','div.StandardArticleBody_body_1gnLA'],
    ['Brookings','http://www.brookings.edu',
     'h1','div.post-body']
]
websites = []
urls = [
    'http://shop.oreilly.com/product/0636920028154.do',
    'http://www.reuters.com/article/us-usa-epa-pruitt-idUSKBN19W2D0',
    'https://www.brookings.edu/blog/techtank/2016/03/01/idea-to-retire-old-methods-of-policy-education/'
]
for row in siteData:
    websites.append(Website(row[0],row[1],row[2],row[3]))
    
crawler.parse(websites[0],urls[0])
crawler.parse(websites[1],urls[1])
crawler.parse(websites[2],urls[2])