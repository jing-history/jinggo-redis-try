/**
 * Created by gz12 on 2018-11-14.
 */
// 创建一个mp3播放器
// var audioDom = document.createElement("audio");
var audioDom = document.getElementById("audio");
// 播放索引的记录
var playindex = 0;
// 音乐列表的总数
var len = $("#l_box").children().length;

// 监听时间
function timeEvent() {
    // 音频加载完毕监听
    audioDom.oncanplaythrough = function () {
        // 获取总时长
        var time = this.duration;
        // 格式化时间
        var ftime = formartTime(time);
        $(".t_start").text(ftime);
    }

    // 播放事件监听
    audioDom.ontimeupdate = function () {
        // 获取总时长
        var time = this.duration;
        // 获取播放时长
        var stime = this.currentTime;
        // 格式化时间
        var ftime = formartTime(stime);
        $(".t_end").text(ftime);
        // 获取播放进度
        var pbit = stime / time;
        // 计算百分比
        var percent = pbit * 100;
        $(".b_sroll").width(percent + "%");
        $(".b_flag").css("left", (percent) + "%");
    }

    // 音乐播放结束
    audioDom.onended = function () {
        if (mark == 2) {
            nextMusic();
        } else {
            randomMusic();
        }

    }
}
    // 格式化日期
    function formartTime(time){
        var m = Math.floor(time / 60);
        var s = Math.floor(time % 60);
        return (m<10 ? "0" + m:m) + ":" + (s<10 ? "0" + s:s);
    }

    // 添加音乐
    function addMusic(src){
        audioDom.src = src;
    }
    // 播放音乐
    function playMusic(obj){
        audioDom.play();
    }

    // 动态替换歌名
    function repeatName(obj){
        var name = obj.find("a").text();
        $("#l_name_new").text(name);
    }

    // 下一首
    function nextMusic(){
        playIndex = (playIndex == (len-1) ? 0 : ++playIndex);
        // 选中音乐文件
        var sel = $("#l_box").find(".b_items").eq(playIndex);
        var src = sel.data("src");
        addMusic(src);
        // 播放选中的音乐
        playMusic(sel);
        sel.addClass("b_sel").siblings().removeClass("b_sel");
    }

    // 上一首
    function prevMusic(){
        playIndex = (playIndex < 0 ? (len-1) : --playIndex);
        // 选中音乐文件
        var sel = $("#l_box").find(".b_items").eq(playIndex);
        var src = sel.data("src");
        addMusic(src);
        // 播放选中的音乐
        playMusic(sel);
        sel.addClass("b_sel").siblings().removeClass("b_sel");
    }

    // 随机播放音乐
    function randomMusic(){
        var random = Math.floor(Math.random()*len);
        playIndex = random;
        var sel = $("#l_box").find(".b_items").eq(playIndex);
        var src = sel.data("src");
        // 添加音乐
        addMusic(src);
        // 播放选中的音乐
        playMusic(sel);
        sel.addClass("b_sel").siblings().removeClass("b_sel");
    }

    // 顺序播放2, 随机播放1
    var mark = 2;

    $(function(){

        // 初始化监听事件
        timeEvent();

        // 点击播放按钮
        $("#play").click(function(){
            // 如果已经有选中播放文件就直接播放
            var sel = $("#l_box").find(".b_sel");
            var src = sel.data("src");
            if(!src){
                // 如果没有把第一当做播放的元素时
                sel = $("#l_box").find("li").eq(0);
                src = sel.data("src");
                // 添加到音乐播放器
                addMusic(src);
                sel.addClass("b_sel");
            }

            playMusic(sel);
            // 切换成暂停按钮
            $("#play").hide().next().show();

            // 将索引记录下来
            playIndex = sel.index();

        });

        // 点击暂停按钮
        $("#stop").click(function(){
            // 调用暂停方法
            audioDom.pause();
            // 切换成播放按钮
            $("#stop").hide().prev().show();
        });


        // 点击音乐列表播放
        $("#l_box").find(".b_items").click(function(){
            // 获取播放音乐文件的地址
            var src = $(this).data("src");
            // 添加到音乐播放器
            addMusic(src);
            // 播放音乐
            playMusic($(this));

            $(this).addClass("b_sel").siblings().removeClass("b_sel");

            // 点击音乐列表文件，记录索引
            playIndex = $(this).index();

            // 自动触发播放按钮
            $("#play").trigger("click");

        });

        // 点击下一首
        $(".next").click(function(){
            nextMusic();
        });
        // 点击上一首
        $(".prev").click(function(){
            prevMusic();
        });

        // 随机播放和顺序播放
        $(".mark").click(function(){
            mark = $(this).data("mark");
            $(".mark").removeClass("sel");
            $(this).addClass("sel")
            if(mark == 2){
                nextMusic();
            } else {
                randomMusic();
            }
        });

        // 收起
        $(".expand").click(function(){
            $(".m_list").animate({left:-310},function(){
                $("#music").animate({width:300});
            });
        });

        // 展开
        $(".expandon").click(function(){
            $("#music").width(610);
            $(".m_list").animate({left:0});
        });

        // 拖动播放
        $(".b_2").mousedown(function(e){
            // 获取拖动对象
            var _this = $(this);
            // 获取鼠标的位置
            var x = e.clientX || e.pageX;
            // 获取拖动元素的起点位置
            var left = _this.position().left;
            // 获取拖动元素的终点位置
            var maxLeft = _this.parent().width() - 10;
            // 先暂停音乐
            audioDom.pause();

            // 拖动元素开始
            $(document).mousemove(function(e){
                // 获取鼠标拖动最终位置
                var n1 = (e.clientX || e.pageX) - x + left;
                // 判断边界
                if(n1 < 0){n1 = 0;}
                if(n1 > maxLeft){n1 = maxLeft;}
                // 定位拖动进度最终位置
                _this.css("left",n1);
                // 根据拖动的位置除以最大位置得到百分比
                var percent = (n1 / maxLeft) * 100;
                // 给进度条和拖动元素赋值
                $(".b_sroll").width(percent+"%");
                $(".b_flag").css("left",(percent-2)+"%");

                // 音乐文件的时间加载
                audioDom.currentTime = audioDom.duration * (n1 / maxLeft);

            }).mouseup(function(){
                // 松开鼠标，播放音乐
                audioDom.play();
                // 移除事件
                $(document).unbind("mousemove");
                $(document).unbind("mouseup");
            });

        });

        // 点击进度条
        $(".b_all").mousedown(function(e){
            // 获取点击位置
            var _this = $(this);
            // 获取鼠标位置
            var x = e.clientX || e.pageX;
            // 获取起点位置
            var left = _this.parent().offset().left;
            // 获取终点位置
            var maxWidth = _this.parent().width();
            // 最大位置
            var w = x - left;
            // 根据点击位置除以最大位置得到百分比
            var percent = (w / maxWidth) * 100;
            // 根据进度赋值
            $(".b_sroll").width(percent+"%");
            $(".b_flag").css("left",(percent-2)+"%");

            // 音乐文件的时间加载
            audioDom.currentTime = audioDom.duration * (w/maxWidth);

        });
        
        // 加载歌词
        function loadLrc(name){

        }
        
        $(function () {
            var text = $("#lrc").val();
            // 把时间个歌词分离出来
            var lrcArr = text.split("[");
            var htmlLrc = "";
            for(var i = 0; i < lrcArr.length; i++){
                // 第二次分割“]”
                var arr = lrcArr[i].split("]");
                // console.log(arr);
                // 取到歌词
                var message = arr[1];

                // 取到时间
                var timer = arr[0].split(".");
                // 取到分钟和秒
                var stime = timer[0].split(":");
                // 转换成秒数
                var ms = stime[0]*60 + stime[1]*1 - 1;

                if(message){
                    htmlLrc += "<div class='lrcline' id='"+ms+"'>"+message+"</div>";
                }
            }
            // 把解析好的歌词放入div中
            $(".l_con").html(htmlLrc);

            // 联动音乐播放歌词
            // 联动音乐播放歌词
            audioDom.addEventListener("timeupdate",function(){
                // 获取当前播放时间
                var timer = this.currentTime;
                console.log(timer);
                // 解析音乐对应的时间
                var m = parseInt(timer / 60);
                var s = parseInt(timer);
                for(var i = 0; i < s; i++){
                    $("#"+i).addClass("lrcSel").siblings().removeClass("lrcSel");
                }
                var st = m * 60 + s;
                $(".l_con").scrollTop(st*2);

            });
        });

    });
