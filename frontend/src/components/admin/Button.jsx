export function Button({ children, style, onclick }){
    return (
        <button className="button" style={{...style}} onClick={onclick ?? console.log}>
            {children}
        </button>
    )
}