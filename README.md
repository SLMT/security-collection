# Security Collection

這個 project 包含多個與資訊安全相關的程式。目前內含以下幾種程式：

- MD5 破解器 (MD5 Cracker)

程式都可以在 `bin` 資料夾中找到，必須在含有 JRE 或 JDK 的環境下執行。

以下個別介紹各程式的使用方式與原理。

## MD5 Cracker

### 原理

基本上 MD5 破解法不外乎就是 Trial & Error，也就是將各種可能性都做 MD5 hash，然後與目標比對看看，以找到正確的結果。這裡一共包含兩種嘗試方式：

- 暴力破解法 (Brute-force Attack)
- 字典破解法 (Dictionary Attack)

第一種方式就是盡可能地嘗試所有組合，此程式會把 a~z, A~Z 與 0~9 所有字元的組合都嘗試一遍。

第二種方式則是事先準備一個字典，讓程式把字典內的字詞全部嘗試一遍。因為通常使用者會使用有意義的文字作為密碼，所以字典法可以大大縮小嘗試的可能性，大大加速查找速度。缺點是，如果使用者並非使用有意義的文字，那麼就很難用字典法找到。

### Brute-force Attack

程式名稱：`bruteForceAttack.jar`

參數：

```
Arguments: [Max Length] [MD5 Hash To Be Tested] ([Salt])
```

參數說明：
- [Max Length]: 嘗試字詞時的最長長度
- [MD5 Hash To Be Tested]: 要破解的目標 MD5 Hash
- [Salt]: (選用) 接在原字詞後的多餘字串

#### Example 1

```
> java -jar bruteForceAttack.jar 3 47bce5c74f589f4867dbd57e9ca9f808
There are 242234 waiting to be tested.
Found it!!! It's 'aaa'.
```

#### Example 2

```
> java -jar bruteForceAttack.jar 3 57a8bdf56042b790fee5c0d54f33a746 123
There are 242234 waiting to be tested.
Found it!!! It's 'ccc'.
```

### Dictionary Attack

程式名稱：`dictionaryAttack.jar`

參數：

```
Arguments: [Dictionary File] [MD5 Hash To Be Tested] ([Salt])
```

參數說明：
- [Dictionary File]: 字典檔位置
- [MD5 Hash To Be Tested]: 要破解的目標 MD5 Hash
- [Salt]: (選用) 接在原字詞後的多餘字串

#### Example 1

```
> java -jar dictionaryAttack.jar ../dict.txt 2f7b22bac3f5542bb48fb08b04141ecb
Found it!!! It's 'rocks'.
```

p.s. `../dict.txt` 是預先準備的字典檔

#### Example 2

```
> java -jar dictionaryAttack.jar ../dict.txt e443093be821472478d44add006c34a8 abc
Found it!!! It's 'roof'.
```
