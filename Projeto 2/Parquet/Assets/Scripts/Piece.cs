using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using System.Collections.Generic;

public class Piece : EventTrigger
{
    public Color mColor = Color.clear;
    protected Cell mOriginalCell = null;
    protected Cell mCurrentCell = null;
    protected RectTransform mRectTransform = null;
    protected PieceManager mPieceManager;

    protected Vector3Int mMovement = Vector3Int.one;
    protected List<Cell> mHighlightedCells = new List<Cell>();

    public void Setup(Color newTeamColor, Color32 newSpriteColor, PieceManager newPieceManager)
    {
        mPieceManager = newPieceManager;
        mColor = newTeamColor;
        GetComponent<Image>().color = newSpriteColor;
        mRectTransform = GetComponent<RectTransform>();
    }

    public void Place(Cell newCell)
    {
        mCurrentCell = newCell;
        mOriginalCell = newCell;
        mCurrentCell.mCurrentPiece = this;
        transform.position = newCell.transform.position;
        gameObject.SetActive(true);
    }

    public override void OnBeginDrag(PointerEventData eventData)
    {
        base.OnBeginDrag(eventData);
    }

    public override void OnDrag(PointerEventData eventData)
    {
        base.OnDrag(eventData);
        transform.position += (Vector3)eventData.delta;
    }

    public override void OnEndDrag(PointerEventData eventData)
    {
        base.OnEndDrag(eventData);
    }

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    /*
    void Update()
    {
        Color Player1Color = Color.blue;
        Color Player2Color = Color.red;
        if (mColor == Player1Color)
        {
            if (Input.GetKeyDown(KeyCode.UpArrow))
            {
                Vector3 newpos = transform.position;
                newpos.y += 43;
                transform.position = newpos;
            }
            if (Input.GetKeyDown(KeyCode.DownArrow))
            {
                Vector3 newpos = transform.position;
                newpos.y -= 43;
                transform.position = newpos;
            }
            if (Input.GetKeyDown(KeyCode.RightArrow))
            {
                Vector3 newpos = transform.position;
                newpos.x += 43;
                transform.position = newpos;
            }
            if (Input.GetKeyDown(KeyCode.LeftArrow))
            {
                Vector3 newpos = transform.position;
                newpos.x -= 43;
                transform.position = newpos;
            }
        }
        if (mColor == Player2Color)
        {
            if (Input.GetKeyDown(KeyCode.W))
            {
                Vector3 newpos = transform.position;
                newpos.y += 43;
                transform.position = newpos;
            }
            if (Input.GetKeyDown(KeyCode.S))
            {
                Vector3 newpos = transform.position;
                newpos.y -= 43;
                transform.position = newpos;
            }
            if (Input.GetKeyDown(KeyCode.D))
            {
                Vector3 newpos = transform.position;
                newpos.x += 43;
                transform.position = newpos;
            }
            if (Input.GetKeyDown(KeyCode.A))
            {
                Vector3 newpos = transform.position;
                newpos.x -= 43;
                transform.position = newpos;
            }
        }
    }
    */
}
