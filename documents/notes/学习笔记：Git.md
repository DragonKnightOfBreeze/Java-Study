Git忽略已提交的文件：

```
git rm -r --cached . #删除追踪状态
git add . 
git commit -m "fixed untracked files"
```