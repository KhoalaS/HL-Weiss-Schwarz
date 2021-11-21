class GalleryItem extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});

        const wrapper = document.createElement('div')
        const a = document.createElement('a')
        const img = document.createElement('img')

        img.setAttribute('src', '')
        img.setAttribute('onclick', 'expandImg')

        a.appendChild(img)
        wrapper.appendChild(a)

        this.shadowRoot.append(wrapper);

    }

}

customElements.define('gallery-item', GalleryItem);

function getInventoryDepr(){
    $.getJSON('user/getInv', function (data){
        $("ul#output > li").remove();
            $.each(data, function (key, value) {
                 $("#output").append('<li>' + value.idol + '</li>');
                        });
    });
}

function getInventory(){
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onload = function() {
        const myObj = JSON.parse(this.responseText);
        myObj.forEach(populateInv);
    };
    xmlhttp.open("GET", "/user/getInv");
    xmlhttp.send();
}

function populateInv(value){
    let item = document.createElement('gallery-item')
    item.setAttribute('class', 'inv')
    item.setAttribute('data-all',JSON.stringify(value))

    const shadow = item.shadowRoot;
    const img = shadow.querySelector('img')
    img.setAttribute('src', 'images/'+value.png)
    img.setAttribute('width', '100%')
    img.style.borderRadius = '10px'

    let invBox = document.getElementById('invBox')
    invBox.appendChild(item)
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
    let cross = document.createElement('img')
    let overlay = document.createElement('div')

    overlay.setAttribute("class", "overlay")
    close.setAttribute("class", "uiButton")
    cross.setAttribute("class", "icon")
    cross.setAttribute("src", "images/cross.svg")
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
        img.setAttribute("src", "images/placeholder_card.png")
        img.setAttribute("id", "card"+i)
        img.setAttribute("onclick", "swap(this)")
        a.appendChild(img)
        top.appendChild(a);
        top.setAttribute("class", "gallery")
        p.appendChild(top);
    }

    document.querySelector("#main").appendChild(p)

    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onload = function() {
      const myObj = JSON.parse(this.responseText);
      myObj.forEach(populatePopUp);
    };
    xmlhttp.open("GET", "/booster");
    xmlhttp.send();
}

function populatePopUp(value, index){
    let card = document.getElementById("card"+index)
    card.setAttribute("iSwap", "images/"+value.png)
}

function swap(e){
    var uImg = e.getAttribute("iSwap")
    e.setAttribute("src", uImg)
}