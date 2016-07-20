.class public Lcom/DecryptFunc;
.super Ljava/lang/Object;
.source "DecryptFunc.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 3
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static decryptionMethod(Ljava/lang/String;)Ljava/lang/String;
    .locals 4
    .parameter "str"

    .prologue
    .line 6
    invoke-virtual {p0}, Ljava/lang/String;->toCharArray()[C

    move-result-object v2

    .line 8
    .local v2, teststr:[C
    const/4 v0, 0x0

    .local v0, i:I
    :goto_0
    array-length v3, v2

    if-lt v0, v3, :cond_0

    .line 26
    invoke-static {v2}, Ljava/lang/String;->valueOf([C)Ljava/lang/String;

    move-result-object p0

    .line 28
    return-object p0

    .line 10
    :cond_0
    aget-char v1, v2, v0

    .line 11
    .local v1, t:I
    const/16 v3, 0x7a

    if-gt v1, v3, :cond_2

    const/16 v3, 0x61

    if-lt v1, v3, :cond_2

    .line 14
    add-int/lit8 v1, v1, -0x61

    .line 15
    add-int/lit8 v3, v1, -0xa

    rem-int/lit8 v1, v3, 0x1a

    .line 16
    if-gez v1, :cond_1

    .line 17
    add-int/lit8 v1, v1, 0x1a

    .line 18
    :cond_1
    add-int/lit8 v1, v1, 0x61

    .line 23
    :cond_2
    int-to-char v3, v1

    aput-char v3, v2, v0

    .line 8
    add-int/lit8 v0, v0, 0x1

    goto :goto_0
.end method
