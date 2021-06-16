# Battle_with_Boice

## ゲームについて

- 各ステージいる敵を倒していくゲームです
- Attack Cure Defense の三つから一つを選択して敵のHPを削り、敵のHPが0になったら次のステージに行けます
    - Attackでは敵のダメージを減らします
    - Cureでは自分のHPを回復します
    - Defenceでは自分のダメージが減らされないようにします
- 選択したキャラによって、アクションの効果が変化します
- 各ステージをクリアするごとにアイテムを獲得できて、そのアイテムによって様々な効果を得られます
- かかった時間の合計がスコアになります
- 三つの難易度が用意されていて、敵のHPと自分のHPがそれぞれ異なります
- 特別なモードとして、voice Recording モードがあります。
    - ここでは録音した自分の声がキャラボイスとして流れます

## ルール
- AttackとDefenceならDefenceのみが成功します
- DefenceとCureならCureのみが成功します
- CureとAttackならAttackのみが成功します
- それ以外の組み合わせならそれぞれの行動が成功します

## 実行環境
- javaFX

