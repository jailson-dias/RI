import requests as rq
from bs4 import BeautifulSoup as bs
import codecs

def getPage(url, name):
    page = rq.get(url)
    print(page)
    f = codecs.open(name, 'w', 'latin1')
    f.write(page.text)
    f.close
    # parsed_page = BeautifulSoup(page.text,"html.parser")

# url = "https://www.lojasrenner.com.br/p/jaqueta-bomber-ultra-leve/-/A-544333014-br.lr?sku=544590918"
# getPage(url, "page1.html")

def beautiful(arquivo): 
    parsed_page = bs(open(arquivo),"html.parser")
    #print(parsed_page)
    print(parsed_page.prettify()[:1000])

beautiful("page1.html")