from selenium import webdriver
from bs4 import BeautifulSoup
driver = webdriver.Chrome('C:/Users/Users/Desktop/web/chromedriver')

driver.implicitly_wait(3)

driver.get('http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788969651365&orderClick=LAG&Kc=')

driver.implicitly_wait(2)
driver.find_element_by_xpath('//*[@id="btnStockOpen"]').click()

html = driver.page_source
soup = BeautifulSoup(html, 'html.parser')
print(soup)

driver.get('http://www.kyobobook.co.kr/prom/2013/general/StoreStockTable.jsp?barcode=9788969651365&ejkgb=KOR')
html2 = driver.page_source
soup2 = BeautifulSoup(html2, 'html.parser')