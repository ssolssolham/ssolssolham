# 현재 excel 파일로 저장하는데 db에 바로 저장하는 걸로 변경하기

import requests
from bs4 import BeautifulSoup
from datetime import datetime
# import openpyxl
from multiprocessing import Pool
from time import sleep


def getBoardContents(toPage):
    processedPages = 5  # 5페이지 단위로 크롤링
    books = []

    for i in range(processedPages * (toPage - 1) + 1, processedPages * toPage):

        url = "https://www.aladin.co.kr/shop/common/wbest.aspx?BestType=Bestseller&BranchType=1&CID=0&page=" + str(
            i) + "&cnt=1000&SortOrder=1"  # page에 따른 url 셋팅

        print(url)

        html = BeautifulSoup(requests.get(url).text, 'html.parser')
        sleep(0.3)

        titles = html.select(".bo3")

        for title in titles:
            print(title)
            href = title.get('href')
            isbn = href[href.find("ItemId=") + 7:]
            title = title.text

            book = {}
            book['href'] = href
            book['isbn'] = isbn
            book['title'] = title

            books.append(book)

    print(books)

    for book in books:
        request_html = requests.get(book['href']).text

        sleep(0.3)

        # html = BeautifulSoup(requests.get(book['href']).text, 'html.parser')
        file_name = "aladin_" + book['isbn'] + "_" + datetime.now().strftime('%Y%m%d%H%M%S')
        f = open("./data/" + file_name + ".html", "w")
        f.write(request_html)
        f.close()
        # info_list = html.select(".info_list")
        # for info in info_list:
        #     print(info)


if __name__ == '__main__':
    # 사용하고자 하는 프로세스 수
    # 너무 크면 연결하고자 하는 페이지에서 막을수도 있다.
    pool = Pool(processes=4)

    # 몇개의 개수만큼 사용할지..
    toPage = 4

    pool.map(getBoardContents, range(1, toPage, 1))
