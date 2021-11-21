function getInventory(){
    $.getJSON('user/getInv', function (data){
        $("ul#output > li").remove();
            $.each(data, function (key, value) {
                 $("#output").append('<li>' + value.idol + '</li>');
                        });
    });
}

function booster(){
    $(document).ready(function(){
        $.get('/booster');
        setTimeout(() => {getInventory();}, 2000)
    })
}

function pop(){
    let main = document.getElementById("main")
    let p = document.createElement("div");
    p.setAttribute("class", "popUp")

    let bar = document.createElement('div')
    let title = document.createElement('span')
    let close = document.createElement('button')
    let cross = document.createElement('span')
    let overlay = document.createElement('div')

    overlay.setAttribute("class", "overlay")
    close.setAttribute("class", "uiButton")
    cross.setAttribute("class", "icon")
    title.setAttribute("class", "title")
    close.appendChild(cross)
    title.textContent = 'Result'
    bar.appendChild(title)
    bar.appendChild(close)
    bar.setAttribute("class", "bar")
    p.appendChild(bar)
    main.appendChild(overlay)
    



    for(let i = 0; i < 8; i++){
        let top = document.createElement('div')
        let a = document.createElement('a')
        let img = document.createElement('img')
        img.setAttribute("src", "placeholder_card.png")
        a.appendChild(img)
        top.appendChild(a);
        top.setAttribute("class", "gallery")
        p.appendChild(top);
    }

    document.querySelector("#main").appendChild(p)

    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onload = function() {
      const myObj = JSON.parse(this.responseText);
      console.log(myObj[0].idol);
    };
    xmlhttp.open("GET", "/booster");
    xmlhttp.send();




}