# 概要

- 総明会グルメガイドというPDFファイルが配布されている(http://www.meiji-soumeikai.com/gourmetguide/)

- PDFファイルのままでは見にくいので、スマホアプリにして常時使ってもらいたい



# 実装案

- 現在地からの距離で検索できるようにする

- 部活動でフィルタリング(恐らくはプルダウンメニュー)

- お店をタップするとそのお店のページとか食べログのページに飛べるようにする

- そこまで多くの店がないため、多くの機能を盛り込む必要もないかなと思っている



# 論理設計・リレーションなど

- 本来であればもう少し細分化する必要があるが、実装が面倒になるので以下の分割で行います

## shops

- id

- name(例：丸二商店)

- category(例：和食)

- phone_number(例：03-3541-1531)

- address(例：東京都中央区築地4-10-16)

- latitude(緯度。例：35.665618)

- longitude(経度。例：139.770348)

- link(例：https://tabelog.com/tokyo/A1313/A131301/13179459/)

- comment(例：毎日丁寧に仕込んでいます。席数に限りがございますので予約してお越しください!!)

## owners

- id

- name(例：明治太郎)→苗字と名前は分ける？

- club(例：生徒会本部)

- graduated_at(例：昭和61年)

- group(例：明紫会)

## shops_owners

- shop_id

- owner_id



# 思考メモ

## 総明会グルメガイド→総明会グルメ

- ホーム画面に表示する際の文字数制限的に、総明会グルメという名前に決定

## 2人以上経営している場合には？(shops_ownersの関係)

- 一番若い人を1人だけ表示する方向性にしようかしら（アプリを使う人は若い人の方が多いという単純な観測の元）



# 実装参考文献

- http://asky.hatenablog.com/entry/2016/05/04/194303

- http://android.roof-balcony.com/intent/browser/

- https://qiita.com/yasumodev/items/44f7fd84a58b709a0349