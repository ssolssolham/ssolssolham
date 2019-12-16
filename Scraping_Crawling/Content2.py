class Content:
    """
    글/페이지 전체에 사용할 기반 클래스
    """
    
    def __init__(self, topic, url, title, body):
        self.url = url
        self.title = title
        self.body = body
        self.topic = topic
    
    def print(self):
        """
        출력 결과를 원하는 대로 바꿀 수 있는 함수
        """
        print('New article found for topic: {}'.format(self.topic))
        print("URL:{}".format(self.url))
        print("TITLE: {}".format(self.title))
        print("BODY:\n{}".format(self.body))

        
class Website:
    """
    웹사이트 구조에 관한 정보를 저장할 클래스
    """
    
    def __init__(self, name, url, searchUrl, resultListing,
                 resultUrl, absoluteUrl, titleTag, bodyTag):
        self.name = name
        self.url = url
        self.searchUrl = searchUrl
        self.resultListing = resultListing
        self.resultUrl = resultUrl
        self.absoluteUrl = absoluteUrl
        self.titleTag = titleTag
        self.bodyTag = bodyTag
        